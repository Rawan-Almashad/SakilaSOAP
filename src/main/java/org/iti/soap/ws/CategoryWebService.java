package org.iti.soap.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import org.iti.soap.dto.CategoryResponse;
import org.iti.soap.dto.CreateCategoryRequest;
import org.iti.soap.dto.UpdateCategoryRequest;
import org.iti.soap.factory.ServiceFactory;
import org.iti.soap.service.CategoryService;
import java.util.List;

@WebService(
        name = "CategoryWebService",
        serviceName = "CategoryWebService",
        targetNamespace = "http://category.ws.soap.iti.org/"
)
public class CategoryWebService {
    private CategoryService categoryService = ServiceFactory.getCategoryService();

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.CreateCategory")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.CreateCategoryResponse")
    public CategoryResponse create(@WebParam(name = "request") CreateCategoryRequest request) {
        return categoryService.create(request);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.FindByIdCategory")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.FindByIdCategoryResponse")
    public CategoryResponse findById(@WebParam(name = "id") Short id) {
        return categoryService.findById(id);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.FindAllCategories")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.FindAllCategoriesResponse")
    public List<CategoryResponse> findAll() {
        return categoryService.findAll();
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.UpdateCategory")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.UpdateCategoryResponse")
    public CategoryResponse update(@WebParam(name = "request") UpdateCategoryRequest request) {
        return categoryService.update(request);
    }

    @WebMethod
    @RequestWrapper(className = "org.iti.soap.ws.jaxws.DeleteCategory")
    @ResponseWrapper(className = "org.iti.soap.ws.jaxws.DeleteCategoryResponse")
    public boolean delete(@WebParam(name = "id") Short id) {
        return categoryService.deleteById(id);
    }
}