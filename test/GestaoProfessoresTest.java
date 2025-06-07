import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GestaoProfessoresTest {

    @Test
    void salarioProfessorCLT() {
        Professor p = new ProfessorCLT("Ana", 4000.0);
        assertEquals(4000.0, p.calcularSalario());
    }

    @Test
    void salarioProfessorHorista() {
        Professor p = new ProfessorHorista("Carlos", 20, 50.0);
        assertEquals(1000.0, p.calcularSalario());
    }

    @Test
    void salarioProfessorPJ() {
        Professor p = new ProfessorPJ("Beatriz", 7000.0);
        assertEquals(7000.0, p.calcularSalario());
    }
}