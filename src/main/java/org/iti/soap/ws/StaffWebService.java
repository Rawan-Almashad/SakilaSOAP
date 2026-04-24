package org.iti.soap.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import org.iti.soap.dto.CreateStaffRequest;
import org.iti.soap.dto.StaffResponse;
import org.iti.soap.dto.UpdateStaffRequest;
import org.iti.soap.factory.ServiceFactory;
import org.iti.soap.service.StaffService;
import java.util.List;

@WebService(
        name = "StaffWebService",
        serviceName = "StaffWebService",
        targetNamespace = "http://staff.ws.soap.iti.org/"
)
public class StaffWebService {
    private StaffService staffService = ServiceFactory.getStaffService();

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.CreateStaff")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.CreateStaffResponse")
    public StaffResponse create(@WebParam(name = "request") CreateStaffRequest request) {
        return staffService.create(request);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.FindByIdStaff")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.FindByIdStaffResponse")
    public StaffResponse findById(@WebParam(name = "id") Byte id) {
        return staffService.findById(id);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.FindAllStaff")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.FindAllStaffResponse")
    public List<StaffResponse> findAll() {
        return staffService.findAll();
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.UpdateStaff")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.UpdateStaffResponse")
    public StaffResponse update(@WebParam(name = "request") UpdateStaffRequest request) {
        return staffService.update(request);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.DeleteStaff")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.DeleteStaffResponse")
    public boolean delete(@WebParam(name = "id") Byte id) {
        return staffService.deleteById(id);
    }
}