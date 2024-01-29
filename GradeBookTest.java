import static org.junit.Assert.*;

public class GradeBookTest {

    public GradeBook gradeBook;

    @org.junit.Before
    public void setUp() throws Exception {
        gradeBook = new GradeBook(10);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        gradeBook = null;
    }

    @org.junit.Test
    public void addScore() {
        gradeBook.addScore(5.2);
        assertEquals(gradeBook.sum(), 5.2, 0.00001);
        gradeBook.addScore(2.1);
        assertEquals(gradeBook.sum(), 7.3, 0.00001);
    }

    @org.junit.Test
    public void sum() {
        gradeBook.addScore(2);
        gradeBook.addScore(6.7);
        assertEquals(gradeBook.sum(), 8.7, 0.00001);
    }

    @org.junit.Test
    public void minimum() {
        gradeBook.addScore(2);
        gradeBook.addScore(6.7);
        gradeBook.addScore(8.7);
        gradeBook.addScore(1.2);
        assertEquals(gradeBook.minimum(), 1.2, 0.001);
    }

    @org.junit.Test
    public void finalScore() {
        assertEquals(gradeBook.finalScore(), 0, 0.000001);
        gradeBook.addScore(2);
        gradeBook.addScore(4.5);
        gradeBook.addScore(3.2);
        assertEquals(gradeBook.finalScore(), gradeBook.sum() - gradeBook.minimum(), 0.0001);
    }

    @org.junit.Test
    public void getScoreSize() {
        gradeBook.addScore(2.3);
        gradeBook.addScore(3.7);
        gradeBook.addScore(5.6);
        gradeBook.addScore(4.5);
        assertEquals(gradeBook.getScoreSize(), 4);
    }

    @org.junit.Test
    public void testToString() {
        gradeBook.addScore(2.3);
        gradeBook.addScore(3.7);
        gradeBook.addScore(5.6);
        gradeBook.addScore(4.5);
        assertEquals(gradeBook.toString(), "2.3 3.7 5.6 4.5");
    }
}