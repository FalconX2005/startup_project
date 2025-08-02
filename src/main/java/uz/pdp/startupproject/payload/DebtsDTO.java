package uz.pdp.startupproject.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.startupproject.enums.Priority;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DebtsDTO {

    private Long id ;

    private Long clientId;

    private Long debtAmount;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Priority priority;

}
