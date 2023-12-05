package com.master.data.location.locationproducer.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Location {
    String locationName;
    String locationType;
    String locationCode;
    String locationCodeType;

    String status;
    String parentLocationType;
    String parentLocationName;
    String parentBdaType;
    String parentAlternateCode;
    String parentAlternateCodeType;

    String bdas;
    String bdaType;
    String countryName;
    String countryAlternateCodes;
    String countryAlternateCodeTypes;

    Date validTo;
    String huluName;
    String latitude;
    String portFlag;
    String timeZone;
    String longitude;
    String validFrom;
    String restricted;
    String description;
    String dialingCode;
    String bdaLocations;
    String isDuskCity;
    String olsonTimezone;

    String alternateNames;
    String subCityParents;
    String utcOffsetMinutes;
    String workaroundReason;
    String daylightSavingEnd;
    String daylightSavingTime;
    String daylightSavingStart;
    String postalCodeMandatory;
    String dialingCodeDescription;
    String stateProvinceMandatory;
    String daylightSavingShiftMinutes;
}
