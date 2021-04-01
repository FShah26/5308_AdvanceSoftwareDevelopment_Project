package com.group9.server.Quiz.Student;

import com.group9.server.Quiz.IQuizPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

public class QuizAssessmentLogicTest {
    IQuizPersistence mockQuizPersistence = Mockito.mock(IQuizPersistence.class);
    IGradingPersistence mockGradingPersistence = Mockito.mock(IGradingPersistence.class);
    IQuizAssessmentLogic underTest;

    static Stream<Arguments> generateAnswersList() {
        List<QuizQuestions> lstQuiz = new ArrayList<>();
        QuizQuestions questions = new QuizQuestions();
        questions.QuestionId = "1";
        questions.Question = "Question1";
        questions.OptionA = "OptionA";
        questions.OptionB = "OptionB";
        questions.OptionC = "OptionC";
        questions.OptionD = "OptionD";
        questions.Solution = "A";
        questions.Answer = "A";
        lstQuiz.add(questions);

        questions = new QuizQuestions();
        questions.QuestionId = "2";
        questions.Question = "Question1";
        questions.OptionA = "OptionA";
        questions.OptionB = "OptionB";
        questions.OptionC = "OptionC";
        questions.OptionD = "OptionD";
        questions.Solution = "B";
        questions.Answer = "C";
        lstQuiz.add(questions);

        return Stream.of(Arguments.of(lstQuiz));
    }

    private ResultSet mockStringResultSet(ArrayList<String> rows) throws SQLException {
        ResultSet rs = Mockito.mock(ResultSet.class);
        OngoingStubbing<Boolean> rsNextStub = when(rs.next());
        for (String ignored : rows) {
            rsNextStub = rsNextStub.thenReturn(true);
        }
        rsNextStub.thenReturn(false);

        OngoingStubbing<String> rsGetStringStub = when(rs.getString(1));
        for (String value : rows) {
            rsGetStringStub = rsGetStringStub.thenReturn(value);
        }

        return rs;
    }

    @BeforeEach
    public void setUp() throws SQLException {
        ArrayList<String> lstStudentCourses = new ArrayList<>();
        lstStudentCourses.add("CSCI222");
        lstStudentCourses.add("CSCI123");
        ResultSet studentCourses = mockStringResultSet(lstStudentCourses);

        ArrayList<String> lstCourseQuiz = new ArrayList<>();
        lstCourseQuiz.add("1");
        lstCourseQuiz.add("2");
        lstCourseQuiz.add("3");
        ResultSet courseQuizzes = mockStringResultSet(lstCourseQuiz);
        Timestamp timestamp;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse("2021-03-28 15:00:00");
            timestamp = new Timestamp(parsedDate.getTime());
            when(mockGradingPersistence.addStudentGrades("hashik", "1", "CSCI123", 50.00, 1, timestamp)).thenReturn(true);
            when(mockGradingPersistence.updateStudentGrades("hashik", "1", "CSCI123", 50.00, 2, timestamp)).thenReturn(true);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        underTest = new QuizAssessmentLogic(mockQuizPersistence, mockGradingPersistence);
        when(mockQuizPersistence.fetchRegisteredCourses("hashik")).thenReturn(studentCourses);
        when(mockQuizPersistence.fetchCourseQuiz("CSCI123")).thenReturn(courseQuizzes);

    }

    @ParameterizedTest
    @CsvSource({
            "hashik,CSCI123"
    })
    @DisplayName("ValidCourseIdForStudent")
    public void validateCourseIdTest(String studentId, String courseId) {
        Assertions.assertEquals(1, underTest.validateCourseId(studentId, courseId));
    }

    @ParameterizedTest
    @CsvSource({
            "hashik,CSCI122"
    })
    @DisplayName("InValidCourseIdForStudent")
    public void inValidateCourseIdTest(String studentId, String courseId) {
        Assertions.assertEquals(0, underTest.validateCourseId(studentId, courseId));
    }

    @ParameterizedTest
    @CsvSource({
            "hashik,CSCI123"
    })
    @DisplayName("Student not enrolled in any course")
    public void noCourseEnrolledTest(String studentId, String courseId) throws SQLException {
        when(mockQuizPersistence.fetchRegisteredCourses("hashik")).thenReturn(null);
        Assertions.assertEquals(-1, underTest.validateCourseId(studentId, courseId));
    }

    @ParameterizedTest
    @CsvSource({
            "CSCI123,1"
    })
    @DisplayName("Valid Quiz number for selected Course")
    public void validateQuizNoTest(String courseId, String quizNumber) {
        Assertions.assertTrue(underTest.validateQuizNo(courseId, quizNumber));
    }

    @ParameterizedTest
    @CsvSource({
            "CSCI123,4"
    })
    @DisplayName("Invalid Quiz number for selected Course")
    public void validateInvalidQuizNoTest(String courseId, String quizNumber) {
        Assertions.assertFalse(underTest.validateQuizNo(courseId, quizNumber));
    }

    @ParameterizedTest
    @CsvSource({
            "CSCI123"
    })
    @DisplayName("Quizzes exist for selected Course")
    public void validateViewQuizForCourseTest(String courseId) {
        Assertions.assertTrue(underTest.viewQuizForCourse(courseId));
    }

    @ParameterizedTest
    @CsvSource({
            "CSCI123"
    })
    @DisplayName("Quizzes does not exist for selected Course")
    public void inValidViewQuizForCourseTest(String courseId) throws SQLException {
        when(mockQuizPersistence.fetchCourseQuiz("CSCI123")).thenReturn(null);
        Assertions.assertFalse(underTest.viewQuizForCourse(courseId));
    }

    @ParameterizedTest
    @MethodSource("generateAnswersList")
    @DisplayName("Compute grade for the given answers")
    public void computeGradesTest(List<QuizQuestions> lstQuiz) {
        Assertions.assertEquals(50.0, underTest.computeGrades(lstQuiz));

    }


    @ParameterizedTest
    @CsvSource({
            "CSCI123,hashik,1,50.00,1,2021-03-28 15:00:00"
    })
    @DisplayName("Update quiz grades for the student")
    public void updateGradesTest(String courseId, String studentId, String quizNumber, double grades, int attempt, Timestamp lastAttemptTimestamp) {
        Assertions.assertTrue(underTest.updateGrades(courseId, studentId, quizNumber, grades, attempt, lastAttemptTimestamp));
    }

    @ParameterizedTest
    @CsvSource({
            "CSCI123,hashik,1,50.00,0,2021-03-28 15:00:00"
    })
    @DisplayName("Add quiz grades for the student")
    public void updateGradesTest2(String courseId, String studentId, String quizNumber, double grades, int attempt, Timestamp lastAttemptTimestamp) {
        Assertions.assertTrue(underTest.updateGrades(courseId, studentId, quizNumber, grades, attempt, lastAttemptTimestamp));
    }


}
