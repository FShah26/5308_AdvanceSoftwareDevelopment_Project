package com.group9.server.Quiz.Student;

import com.group9.server.Login.IUserInputValidator;
import com.group9.server.Meeting.StudentRequestMeeting.MeetingDetails;
import com.group9.server.Quiz.IQuizPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class QuizAssessmentLogic implements IQuizAssessmentLogic {


    IQuizPersistence quizPersistence;
    IGradingPersistence gradingPersistence;
    IUserInputValidator inputOptionsValidator;


    @Autowired
    public QuizAssessmentLogic(IQuizPersistence quizPersistence, IGradingPersistence gradingPersistence) {
        this.quizPersistence = quizPersistence;
        this.gradingPersistence = gradingPersistence;
        this.inputOptionsValidator = new InputAnswerOptionsValidator();
    }

    public List<String> fetchQuizForCourse(String courseId) {
        List<String> lstQuiz = null;
        try {
            ResultSet set = this.quizPersistence.fetchCourseQuiz(courseId);
            if (set != null) {
                lstQuiz = new ArrayList<>();
                while (set.next()) {
                    lstQuiz.add(set.getString(1));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error fetching quiz for courseId");
            System.out.println(ex.getMessage());
        }
        return lstQuiz;
    }

    public List<QuizQuestions> fetchQuiz(String courseId, String quizNumber) {
        List<QuizQuestions> lstQuizQuestions = null;
        try {
            ResultSet set = this.quizPersistence.fetchQuiz(courseId, quizNumber);
            if (set != null) {
                lstQuizQuestions = new ArrayList<>();
                while (set.next()) {
                    QuizQuestions question = new QuizQuestions();
                    question.QuestionId = set.getString(1);
                    question.Question = set.getString(4);
                    question.OptionA = set.getString(5);
                    question.OptionB = set.getString(6);
                    question.OptionC = set.getString(7);
                    question.OptionD = set.getString(8);
                    question.Solution = set.getString(9);
                    question.Answer = "";
                    lstQuizQuestions.add(question);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error fetching questions for quiz!!");
            System.out.println(ex.getMessage());
        }
        return lstQuizQuestions;

    }

    @Override
    public boolean viewQuizForCourse(String courseId) {
        List<String> lstQuiz = fetchQuizForCourse(courseId);
        if (null == lstQuiz) {
            System.out.println("No quiz found for this course");
            return false;
        } else {
            System.out.println("************************************************");
            System.out.println("                   COURSE QUIZ                    ");
            System.out.println("************************************************");
            for (String quizNumber : lstQuiz) {
                System.out.println("Quiz " + quizNumber);
            }
            return true;
        }
    }

    @Override
    public int validateCourseId(String studentId, String courseId) {
        List<String> lstStudentCourse = new ArrayList<>();
        try {
            ResultSet set = quizPersistence.fetchRegisteredCourses(studentId);
            if (null == set || set.next() == false) {
                System.out.println("No course registered for you");
                return -1;
            } else {
                lstStudentCourse.add(set.getString(1));
                while (set.next()) {
                    lstStudentCourse.add(set.getString(1));
                }
            }
            if (lstStudentCourse.contains(courseId)) {
                return 1;
            }

        } catch (SQLException ex) {
            System.out.println("Error validating courseId");
            ex.getMessage();
            return -1;
        }
        System.out.println("Incorrect Course Id");
        return 0;
    }

    @Override
    public boolean validateQuizNo(String courseId, String quizNumber) {
        List<String> lstQuiz = fetchQuizForCourse(courseId);
        try {
            if (lstQuiz.contains(quizNumber)) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Error validating Quiz Number");
            System.out.println(ex.getMessage());
        }
        System.out.println("Invalid quiz number!!");
        return false;
    }

    @Override
    public void startQuiz(String courseId, String studentId, String quizNumber) {
        List<QuizQuestions> lstQuestions = fetchQuiz(courseId, quizNumber);
        if (null == lstQuestions) {
            System.out.println("No questions added to the quiz!!");
        } else {
            int count = 1;
            for (QuizQuestions questions : lstQuestions) {
                out.println("Question " + count + ") " + questions.Question);
                out.println("A) " + questions.OptionA + "       B)  " + questions.OptionB);
                out.println("C) " + questions.OptionC + "       D)  " + questions.OptionD);
                questions.Answer = getValidOptionInput();
                count += 1;
            }
            double grades = computeGrades(lstQuestions);
            int attempt = getQuizAttemptCount(courseId, studentId, quizNumber);
            Timestamp lastAttemptTimeStamp = new Timestamp(System.currentTimeMillis());
            if (updateGrades(courseId, studentId, quizNumber, grades, attempt, lastAttemptTimeStamp)) {
                out.println("Grade : " + grades);
            } else {
                out.println("Error grading the quiz. Please take a re-test!!");
            }

        }
    }

    public int getQuizAttemptCount(String courseId, String studentId, String quizNumber) {
        int attempt = 0;
        try {
            ResultSet set = this.gradingPersistence.fetchPreviousGrades(courseId, studentId, quizNumber);
            if (set != null) {
                while (set.next()) {
                    attempt = set.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error fetching last attempt for quiz!!");
            System.out.println(ex.getMessage());
        }
        return attempt;
    }

    public String getValidOptionInput() {
        Scanner sc = new Scanner(System.in);
        out.println("Choose option : ");
        String option = sc.nextLine();
        while (false == this.inputOptionsValidator.validate(option)) {
            out.println("Enter a valid option (A/B/C/D) : ");
            option = sc.nextLine();
        }
        return option;
    }

    @Override
    public boolean updateGrades(String courseId, String studentId, String quizNumber, double grades, int attempt, Timestamp lastAttemptTimestamp) {
        boolean result = false;
        try {
            if (attempt == 0) {
                attempt += 1;
                result = gradingPersistence.addStudentGrades(studentId, quizNumber, courseId, grades, attempt, lastAttemptTimestamp);
            } else {
                attempt += 1;
                result = gradingPersistence.updateStudentGrades(studentId, quizNumber, courseId, grades, attempt, lastAttemptTimestamp);
            }
        } catch (SQLException ex) {
            out.println("Error updating grades!!");
            out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public double computeGrades(List<QuizQuestions> lstQuestions) {
        double grade = 0.0;
        int total = lstQuestions.size();
        double correct = 0.0;
        for (QuizQuestions question : lstQuestions) {
            if (question.Answer.equalsIgnoreCase(question.Solution)) {
                correct += 1.0;
            }
        }
        grade = (correct / total) * 100.00;
        return grade;
    }

    @Override
    public void viewGrades(String studentId){
        ArrayList<ViewGrades> grades = gradeList(studentId);
        if (grades.size() > 0) {
            System.out.println("__________________________________________________________________________________________________________________");
            System.out.printf("%-20s%-15s%-20s%-35s%-30s\n", "Course ID", "Quiz ID", "Grades", "Attempt","Last Attempt");
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            for (ViewGrades m : grades) {
                System.out.printf("%-20s%-15s%-20s%-35s%-30s\n", m.courseId, m.quizId, m.grades, m.attempt,m.lastAttempt);
            }
        } else {
            System.out.println("Seems like you haven't given any quizzes yet.");
        }
    }

    public ArrayList<ViewGrades> gradeList(String studentId) {
        ArrayList<ViewGrades> grades = new ArrayList<ViewGrades>();
        try{
            ResultSet set = gradingPersistence.grades(studentId);
            while (set.next()) {
                String courseId=set.getString(1);
                String quizId = set.getString(2);
                String quizGrade = set.getString(3);
                String attempt=set.getString(4);
                String lastAttempt=set.getString(5);
                ViewGrades viewGrades = new ViewGrades(courseId,quizId,quizGrade,attempt,lastAttempt);
                grades.add(viewGrades);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return grades;
    }

}
