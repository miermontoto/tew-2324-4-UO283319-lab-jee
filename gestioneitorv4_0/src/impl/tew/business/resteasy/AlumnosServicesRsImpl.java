package impl.tew.business.resteasy;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.tew.business.resteasy.AlumnosServicesRs;
import com.tew.infrastructure.GestorSesion;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Alumno;
import com.tew.model.AlumnoRequestData;

import impl.tew.business.classes.*;

public class AlumnosServicesRsImpl implements AlumnosServicesRs {
	@Override
	public List<Alumno> getAlumnos() throws Exception {
		return new AlumnosListado().getAlumnos();
	}

	@Override
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void saveAlumno(AlumnoRequestData alumno) throws EntityAlreadyExistsException {
		new AlumnosAlta().save(alumno);
	}

	@Override
	public void updateAlumno(Alumno alumno) throws EntityNotFoundException {
		new AlumnosUpdate().update(alumno);
	}
	
	@Override
	public void deleteAlumno(Long id, String token) throws EntityNotFoundException, NotAuthorizedException {
		if (GestorSesion.getInstance().comprobarToken(token) != null) this.deleteAlumno(id);
	}

	@Override
	public Alumno findById(Long id) throws EntityNotFoundException {
		return new AlumnosBuscar().find(id);
	}

	@Override
	public void saveAlumno(Alumno alumno) throws EntityAlreadyExistsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAlumno(Long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
	}
}
