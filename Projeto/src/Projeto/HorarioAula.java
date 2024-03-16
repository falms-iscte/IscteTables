package Projeto;

public class HorarioAula {
		
	    private String curso;
	    private String unidadeCurricular;
	    private String turno;
	    private String turma;
	    private int inscritosNoTurno;
	    private String diaSemana;
	    private String horaInicioAula;
	    private String horaFimAula;
	    private String dataAula;
	    private String caracteristicasSalaPedida;
	    private String salaAtribuida;
	    
	    

	    public HorarioAula(String curso, String unidadeCurricular, String turno, String turma, int inscritosNoTurno,
	                       String diaSemana, String horaInicioAula, String horaFimAula, String dataAula,
	                       String caracteristicasSalaPedida, String salaAtribuida) {
	        this.curso = curso;
	        this.unidadeCurricular = unidadeCurricular;
	        this.turno = turno;
	        this.turma = turma;
	        this.inscritosNoTurno = inscritosNoTurno;
	        this.diaSemana = diaSemana;
	        this.horaInicioAula = horaInicioAula;
	        this.horaFimAula = horaFimAula;
	        this.dataAula = dataAula;
	        this.caracteristicasSalaPedida = caracteristicasSalaPedida;
	        this.salaAtribuida = salaAtribuida;
	    }
	    
	    
	    
	    
	    
}

