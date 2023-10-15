package com.tew.persistence;

import com.tew.persistence.AlumnoDao;

/**
 * Interfaz de la factoria que suministra implementaciones reales de la fachada
 * de persistencia para cada Entidad del Modelo (en este caso solo hay
 * una: Alumno; pero en futuras versiones habrá más)
 *
 * @author Enrique
 *
 */
public interface PersistenceFactory {

	AlumnoDao createAlumnoDao();

	// ... otros métodos factoria para Daos de otras entidades del modelo ...
}
