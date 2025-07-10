package com.ritik.CourseRegistrationSystem.service;
import com.jeetdesaimusic.CourseRegistrationSystem.DTO.*;
import com.ritik.CourseRegistrationSystem.DTO.*;
import com.ritik.CourseRegistrationSystem.Mappers.CourseMapper;
import com.jeetdesaimusic.CourseRegistrationSystem.model.*;
import com.jeetdesaimusic.CourseRegistrationSystem.repository.*;
import com.ritik.CourseRegistrationSystem.model.*;
import com.ritik.CourseRegistrationSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepo arepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private RegistrationFormRepo rfrepo;
    @Autowired
    private StudentRepo srepo;

    @Autowired
    private ProfessorRepo prepo;

    @Autowired
    private SemesterRepo semrepo;

    @Autowired
    private CourseMapper cmapper;

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private CourseRepo crepo;
    public Admin registerAdmin(AdminDTO myAdmin) {
        Admin admin = new Admin();
        admin.setEmail(myAdmin.getEmail());
        admin.setName(myAdmin.getName());
        admin.setPassword(encoder.encode(myAdmin.getPassWord()));
        admin.setRole("ADMIN");
        admin.setAdminCode(myAdmin.getAdminCode());
        return arepo.save(admin);
    }

    public Student registerStudent(StudentDTO studentdto) {
        Student student = new Student();
        student.setName(studentdto.getName());
        student.setEmail(studentdto.getEmail());
        student.setPassword(encoder.encode(studentdto.getPassWord()));
        student.setStudentID(studentdto.getStudentID());
        student.setCurrentSemester(semrepo.findBySemesterName(studentdto.getSemesterName()));
        student.setSemesterNo(studentdto.getSemesterNo());
        student.setRole("STUDENT");
        student.setCpi(studentdto.getCpi());
        return srepo.save(student);
    }

    public Professor registerProfessor(ProfessorDTO professordto) {
        Professor professor = new Professor();
        professor.setName(professordto.getName());
        professor.setEmail(professordto.getEmail());
        professor.setPassword(encoder.encode(professordto.getPassWord()));
        professor.setProfCode(professordto.getProfCode());
        professor.setRole("PROFESSOR");
        return prepo.save(professor);
    }


    public Semester registerSemester(SemesterDTO semesterdto) {
        Semester semester = new Semester();
        semester.setSemesterName(semesterdto.getSemesterName());
        semester.setStartDate(semesterdto.getStartDate());
        semester.setEndDate(semesterdto.getEndDate());
        return semrepo.save(semester);
    }


    public Course registerCourse(CourseDTO coursedto) {
        Course course = cmapper.toEntity(coursedto);
        return crepo.save(course);
    }

    public Course updateCourse(Integer cid,CourseDTO coursedto) {
        Course course = crepo.findById(cid).orElse(null);
        if(course==null)
        {
            System.out.println("Professor not found");
            throw new RuntimeException("Professor not found");
        }
            course.setCourseName(coursedto.getCourseName());
            course.setCredits(coursedto.getCredits());
            course.setCapacity(coursedto.getCapacity());
            course.setRemainingSeats(coursedto.getRemainingSeats());
        return crepo.save(course);
    }

    public Semester updateSemester(Integer sid, SemesterDTO semesterdto) {
        Semester semester = semrepo.findById(sid).orElse(null);
        if(semester==null)
        {
            System.out.println("Semester not found");
            throw new RuntimeException("Semester not found");
        }
        semester.setSemesterName(semesterdto.getSemesterName());
        semester.setStartDate(semesterdto.getStartDate());
        semester.setEndDate(semesterdto.getEndDate());
        return semrepo.save(semester);
    }

    public String deleteStudent(Integer sid) {
        Student student = srepo.findById(sid).orElse(null);
        if(student==null)
            return "No such student exists";
        List<Course> enrolled= student.getEnrolledCourses();
        for(Course c : enrolled)
        {
            c.getEnrolledStudents().remove(student);
        }
        srepo.delete(student);
        return "Successfully deleted";
    }

    public String deleteProfessor(Integer pid) {
        Professor professor = prepo.findById(pid).orElse(null);
        if(professor==null)
            return "No such professor exists";
        prepo.delete(professor);
        return "Successfully deleted";
    }

    public String deleteCourse(Integer cid) {
        Course course = crepo.findById(cid)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        for (Semester semester : course.getSemesterList()) {
            semester.getCourseList().remove(course);
        }

        course.getSemesterList().clear();
        crepo.delete(course);
        return "SUCCESSFULLY DELETED";
    }

    public boolean assignCourses() {
        List<RegistrationForm> responses= rfrepo.findAll();
        if(responses.isEmpty())
            return false;
        responses.sort(Comparator.comparing(RegistrationForm::getSubmissionTime));
        System.out.println("Sorted responses");

        for(RegistrationForm r : responses)
        {
            System.out.println("In response " + r.getId());
            List<Course> allocated = new ArrayList<>();
            int remaining = r.getRequiredCourses();
            for(String preferences : r.getPreferenceList())
            {
                System.out.println("For " + srepo.findById(r.getSid()).get().getName() + " remaining = " + remaining);
                if(remaining==0)
                    break;
                Course c = crepo.findByCourseCode(preferences);
                System.out.println("The course desired was + " + c.getCourseName());
                if(c.getRemainingSeats()!=0) {
                    c.setRemainingSeats(c.getRemainingSeats() - 1);
                    remaining--;
                    allocated.add(c);
                    if (c.getEnrolledStudents() == null)
                        c.setEnrolledStudents(new ArrayList<>());
                    c.getEnrolledStudents().add(srepo.findById(r.getSid()).orElseThrow(() -> new RuntimeException("Student not found")));
                    crepo.save(c);
                    System.out.println("This course was allocated");
                }
            }
            Student s = srepo.findById(r.getSid()).orElseThrow(()->new RuntimeException("Student not found"));
            sendEmailToStudent(s,allocated, !allocated.isEmpty());

        }
        rfrepo.deleteAll();
        return true;
    }

    public void sendEmailToStudent(Student s, List<Course> allocated,boolean successful) {
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Dear ").append(s.getName()).append(",\n\n");

        if (successful) {
            emailContent.append("The following courses have been allocated to you : ").append("\n\n");
            for (Course c : allocated) {
                emailContent.append(c.getCourseCode()).append(" - ").append(c.getCourseName()).append("\n");
            }
        } else
        {
            emailContent.append("Unfortunately, no courses have been allocated to you due to unavailability of seats" +
                    " in your preferences. Kindly change your preferences and submit the form again.");
        }
        emailContent.append("\nBest Regards,\nCourse Registration Team");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(s.getEmail());
        message.setSubject("Allocated Courses");
        message.setText(emailContent.toString());
        sender.send(message);
    }

    public void sendReminderToStudent(Student s,String deadline,String semester) {
        String emailContent = "Dear " + s.getName() + ",\n\n" +
                "You are requested to fill your registration form for the upcoming semester " + semester + " before " +
                deadline + ".\n" +
                "\nBest Regards,\nCourse Registration Team";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(s.getEmail());
        message.setSubject("Course registration reminder");
        message.setText(emailContent);
        sender.send(message);
    }

    public void sendReminder(String semesterName,String deadline) {
        Semester sem = semrepo.findBySemesterName(semesterName);
        List<Student> enrolled = sem.getRegisteredStudents();
        for(Student s : enrolled)
        {
            sendReminderToStudent(s,deadline,semesterName);
        }
    }

//    public boolean sendReminder() {
//
//    }
}