package uz.pdp.startupproject.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.startupproject.entity.Attachment;
import uz.pdp.startupproject.entity.Company;
import uz.pdp.startupproject.entity.Employee;
import uz.pdp.startupproject.entity.User;
import uz.pdp.startupproject.exception.RestException;
import uz.pdp.startupproject.payload.ApiResult;
import uz.pdp.startupproject.payload.EmployeeDTO;
import uz.pdp.startupproject.payload.UserDTO;
import uz.pdp.startupproject.repository.AttachmentRepository;
import uz.pdp.startupproject.repository.EmployeeRepository;
import uz.pdp.startupproject.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by: Umar
 * DateTime: 7/15/2025 1:22 PM
 */
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AttachmentRepository attachmentRepository;
    private final UserRepository userRepository;


    @Transactional
    public ApiResult<List<EmployeeDTO>> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for (Employee employee : employees) {
            if (!employee.isDeleted()) {
                UserDTO userDTO = UserDTO.builder()
                        .role(employee.getUser().getRole())
                        .build();

                EmployeeDTO employeeDTO = EmployeeDTO.builder()
                        .id(employee.getId())
                        .firstName(employee.getFirstName())
                        .lastName(employee.getLastName())
                        .phoneNumber(employee.getPhoneNumber())
                        .attachmentId(employee.getAttachment() != null ? employee.getAttachment().getId() : null)
                        .gender(employee.getGender())
                        .user(userDTO)
                        .build();

                employeeDTOS.add(employeeDTO);
            }
        }

        return ApiResult.success(employeeDTOS);

    }


    @Transactional
    public ApiResult<EmployeeDTO> findById(Long id) {

        Optional<Employee> employeeId = employeeRepository.findById(id);

        if (employeeId.isPresent()) {

            Employee employee = employeeId.get();
            Optional<User> byId = userRepository.findById(employee.getUser().getId());

            if (byId.isPresent()) {
                User user = byId.get();

                UserDTO userDTO = UserDTO.builder()
                        .role(employee.getUser().getRole())
                        .username(user.getUsername())
                        .build();


                if (!employee.isDeleted()) {
                    EmployeeDTO employeeDTO = EmployeeDTO.builder()
                            .id(employee.getId())
                            .firstName(employee.getFirstName())
                            .lastName(employee.getLastName())
                            .phoneNumber(employee.getPhoneNumber())
                            .gender(employee.getGender())
                            .attachmentId(employee.getAttachment().getId())
                            .user(userDTO)
                            .build();
                    return ApiResult.success(employeeDTO);
                }
            }

        }

        throw RestException.notFound("Employee not found", id);

    }


    @Transactional
    public ApiResult<EmployeeDTO> createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setGender(employeeDTO.getGender());

        UserDTO userDTO = employeeDTO.getUser();
        if (userDTO == null) {
            throw RestException.badRequest("User info required");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        userRepository.save(user);
        employee.setUser(user);

        if (employeeDTO.getAttachmentId() != null) {
            Attachment attachmentId = attachmentRepository.findById(employeeDTO.getAttachmentId())
                    .orElseThrow(() -> RestException.notFound("Attachment not found: ", employeeDTO.getAttachmentId()));
            employee.setAttachment(attachmentId);
        }

        employeeRepository.save(employee);

        UserDTO userDTO1 = UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();

        EmployeeDTO empDTO = EmployeeDTO
                .builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .phoneNumber(employee.getPhoneNumber())
                .gender(employee.getGender())
                .attachmentId(employee.getAttachment() != null ? employee.getAttachment().getId() : null)
                .user(userDTO1)
                .build();

        return ApiResult.success(empDTO);

    }


    @Transactional
    public ApiResult<EmployeeDTO> update(Long id, EmployeeDTO employeeDTO) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Employee not found: ", id));

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setGender(employeeDTO.getGender());

        UserDTO userDto = employeeDTO.getUser();
        if (userDto == null) {
            throw RestException.badRequest("User info required");
        }


        User user = employee.getUser();
        if (user == null) {
            throw RestException.notFound("User not found for employee", id);
        }
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        User save = userRepository.save(user);


        if (employeeDTO.getAttachmentId() != null) {

            Attachment attachment = attachmentRepository.findById(employeeDTO.getAttachmentId())
                    .orElseThrow(() -> RestException.notFound("Attachment not found: ", employeeDTO.getAttachmentId()));

            employee.setAttachment(attachment);
        }


        employeeRepository.save(employee);

        UserDTO userDTO = UserDTO.builder()
                .id(save.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();

        if (!employee.isDeleted()) {
            EmployeeDTO buildDTO = EmployeeDTO.builder()
                    .id(employee.getId())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .phoneNumber(employee.getPhoneNumber())
                    .gender(employee.getGender())
                    .attachmentId(employee.getAttachment() != null ? employee.getAttachment().getId() : null)
                    .user(userDTO)
                    .build();

            return ApiResult.success(buildDTO);
        }
        throw RestException.notFound("Employee not found: ", id);
    }


    @Transactional
    public ApiResult<EmployeeDTO> delete(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Employee not found", id));
        employee.setDeleted(true);
        employeeRepository.save(employee);

        return ApiResult.success("Employee deleted successfully");
    }

}