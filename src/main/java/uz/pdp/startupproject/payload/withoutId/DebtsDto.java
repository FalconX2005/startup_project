package uz.pdp.startupproject.payload.withoutId;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class DebtsDto {
    private Long clientId;
    @NotNull
    private Long debtAmount;
    @NotBlank
    private LocalDate fromDate;
    @NotBlank
    private LocalDate toDate;

    @NotBlank
    private Priority priority;

}
