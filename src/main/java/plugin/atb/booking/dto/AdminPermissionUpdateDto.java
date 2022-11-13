package plugin.atb.booking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AdminPermissionUpdateDto {
    private Long idQueueReservation;

    private boolean adminPermission;
}
