package Projeto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

	public int getSemanaDoAno() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		try {
			Date date = sdf.parse(dataAula);
			cal.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	public int getSemanaDoSemestre() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		try {
			Date date = sdf.parse(dataAula);
			cal.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
		int month = cal.get(Calendar.MONTH) + 1; // Janeiro é 0, então adicionamos 1
		// Vamos assumir que um semestre tem 6 meses (de janeiro a junho e de julho a
		// dezembro)
		if (month <= 6) {
			return (weekOfYear - 1) % 15 + 1; // O primeiro semestre tem 15 semanas
		} else {
			return (weekOfYear - 1) % 15 + 1; // O segundo semestre tem 15 semanas
		}
	}

}
