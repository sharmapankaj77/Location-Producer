package com.master.data.location.locationproducer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationEvent {
    Integer locationEventId;
    Object location;

}
