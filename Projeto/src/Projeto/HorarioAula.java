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



		public String getCurso() {
			return curso;
		}



		public void setCurso(String curso) {
			this.curso = curso;
		}



		public String getUnidadeCurricular() {
			return unidadeCurricular;
		}



		public void setUnidadeCurricular(String unidadeCurricular) {
			this.unidadeCurricular = unidadeCurricular;
		}



		public String getTurno() {
			return turno;
		}



		public void setTurno(String turno) {
			this.turno = turno;
		}



		public String getTurma() {
			return turma;
		}



		public void setTurma(String turma) {
			this.turma = turma;
		}



		public int getInscritosNoTurno() {
			return inscritosNoTurno;
		}



		public void setInscritosNoTurno(int inscritosNoTurno) {
			this.inscritosNoTurno = inscritosNoTurno;
		}



		public String getDiaSemana() {
			return diaSemana;
		}



		public void setDiaSemana(String diaSemana) {
			this.diaSemana = diaSemana;
		}



		public String getHoraInicioAula() {
			return horaInicioAula;
		}



		public void setHoraInicioAula(String horaInicioAula) {
			this.horaInicioAula = horaInicioAula;
		}



		public String getHoraFimAula() {
			return horaFimAula;
		}



		public void setHoraFimAula(String horaFimAula) {
			this.horaFimAula = horaFimAula;
		}



		public String getDataAula() {
			return dataAula;
		}



		public void setDataAula(String dataAula) {
			this.dataAula = dataAula;
		}



		public String getCaracteristicasSalaPedida() {
			return caracteristicasSalaPedida;
		}



		public void setCaracteristicasSalaPedida(String caracteristicasSalaPedida) {
			this.caracteristicasSalaPedida = caracteristicasSalaPedida;
		}



		public String getSalaAtribuida() {
			return salaAtribuida;
		}



		public void setSalaAtribuida(String salaAtribuida) {
			this.salaAtribuida = salaAtribuida;
		}
	    
	    
	    
	    
	    
	    
	    
	    
	    
}

