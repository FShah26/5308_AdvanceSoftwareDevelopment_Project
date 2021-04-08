package com.group9.server.Quiz.Student;

import com.group9.server.Login.IUserInputValidator;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
public class QuizAssessment implements IQuizAssessment {

    String studentId;
    IUserInputValidator quizAssessmentOptionValidator;
    IQuizAssessmentLogic quizAssessmentLogic;
    private static final String ASSESS_QUIZ = "1";
    private static final String VIEW_QUIZ = "2";
    private static final int STATE_INVALID = 0;
    private static final int STATE_ERROR = -1;

    public QuizAssessment(IQuizAssessmentLogic quizAssessmentLogic) {
        this.studentId = "";
        this.quizAssessmentOptionValidator = new QuizAssessmentOptionValidator();
        this.quizAssessmentLogic = quizAssessmentLogic;
    }

    @Override
    public void showQuizMenu(String studentId) {
        this.studentId = studentId;
        System.out.println("************************************************");
        System.out.println("                QUIZ ASSESSMENT                 ");
        System.out.println("************************************************");
        System.out.println("Press 1 --> Take a Quiz");
        System.out.println("Press 2 --> View Grades");
        System.out.println("Press * --> Go to main menu ");
        System.out.println("Choose Option:");
        selectMenu();

    }

    public void selectMenu() {
        Scanner sc = new Scanner(System.in);
        String menuOption = sc.nextLine();
        checkInput(menuOption);
    }

    public void checkInput(String selection) {
        if (this.quizAssessmentOptionValidator.validate(selection)) {
            switch (selection) {
                case ASSESS_QUIZ:
                    assessQuiz();
                    break;
                case VIEW_QUIZ:
                    viewQuiz();
                    break;
                default:
                    break;
            }

        } else {
            displayInvalidMenuOptionMsg();
            selectMenu();
        }
    }

    public void displayInvalidMenuOptionMsg() {
        System.out.println("Invalid Option! Please choose a valid option from menu.");
    }

    @Override
    public void assessQuiz() {
        String courseId = getValidCourseIdInput();
        if (courseId.length() > 0) {
            if (quizAssessmentLogic.viewQuizForCourse(courseId)) {
                String quizNo = getValidQuizNoInput(courseId);
                quizAssessmentLogic.startQuiz(courseId, this.studentId, quizNo);
            }
            showQuizMenu(this.studentId);
        }
    }

    @Override
    public void viewQuiz() {
        quizAssessmentLogic.viewGrades(this.studentId);
    }

    public String getValidCourseIdInput() {
        int state = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CourseId: ");
        String courseId = sc.nextLine();
        while ((state = quizAssessmentLogic.validateCourseId(this.studentId, courseId)) == STATE_INVALID) {
            System.out.println("Enter a valid courseID: ");
            courseId = sc.nextLine();
        }
        if (state == STATE_ERROR) {
            return null;
        }
        return courseId;
    }

    public String getValidQuizNoInput(String courseId) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Quiz No : ");
        String quizNo = sc.nextLine();
        while (false == quizAssessmentLogic.validateQuizNo(courseId, quizNo)) {
            System.out.println("Enter a valid quiz number : ");
            quizNo = sc.nextLine();
        }
        return quizNo;
    }

    @Override
    public void execute(String userRole, String userId) {
        showQuizMenu(userId);
    }
}