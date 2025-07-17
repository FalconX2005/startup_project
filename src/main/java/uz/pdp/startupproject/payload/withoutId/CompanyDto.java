package uz.pdp.startupproject.payload.withoutId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyDto {


    private String name ;

    private String email;

    private String phone;

    private String address;


}
