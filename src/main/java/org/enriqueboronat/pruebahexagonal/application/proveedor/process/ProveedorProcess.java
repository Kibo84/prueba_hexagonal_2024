package org.enriqueboronat.pruebahexagonal.application.proveedor.process;

import io.vavr.control.Either;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.CreateProveedorCommand;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.DeleteProveedorCommand;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.SearchProveedorCommand;
import org.enriqueboronat.pruebahexagonal.application.proveedor.command.UpdateProveedorCommand;
import org.enriqueboronat.pruebahexagonal.domain.proveedor.entity.Proveedor;

import java.util.List;

public interface ProveedorProcess {
    List<Proveedor> searchProveedors();
    Either<Error, Proveedor> createProveedor(CreateProveedorCommand command);
    Either<Error, Proveedor> updateProveedor(UpdateProveedorCommand command);
    Either<Error, Void> deleteProveedor(DeleteProveedorCommand command);
    Either<Error, Proveedor> searchProveedorById(SearchProveedorCommand command);
}
