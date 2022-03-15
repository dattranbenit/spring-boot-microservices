package accountservice.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class StatisticDTO {
    private Long id;
    @NonNull
    private String message;
    @NonNull
    private Date createdDate;
}
