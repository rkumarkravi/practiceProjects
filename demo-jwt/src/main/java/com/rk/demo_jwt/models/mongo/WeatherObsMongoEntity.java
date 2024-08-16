package com.rk.demo_jwt.models.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "weathera_data")
@Data
public class WeatherObsMongoEntity {

    @Id
    private String id;

    private String st;

    @Field("ts")
    private Date timestamp;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Position position;

    private int elevation;
    private String callLetters;
    private String qualityControlProcess;
    private String dataSource;
    private String type;

    private Measurement airTemperature;
    private Measurement dewPoint;
    private Measurement pressure;

    private Wind wind;
    private Visibility visibility;
    private SkyCondition skyCondition;

    private List<String> sections;

    private PrecipitationEstimatedObservation precipitationEstimatedObservation;

    // Getters and setters
    @Data
    public static class Position {
        private String type;
        private double[] coordinates;

        // Getters and setters
    }
    @Data
    public static class Measurement {
        private double value;
        private String quality;

        // Getters and setters
    }
    @Data
    public static class Wind {
        private WindDirection direction;
        private String type;
        private WindSpeed speed;

        // Getters and setters
        @Data
        public static class WindDirection {
            private int angle;
            private String quality;

            // Getters and setters
        }
        @Data
        public static class WindSpeed {
            private double rate;
            private String quality;

            // Getters and setters
        }
    }
    @Data
    public static class Visibility {
        private Distance distance;
        private Variability variability;

        // Getters and setters
        @Data
        public static class Distance {
            private int value;
            private String quality;

            // Getters and setters
        }
        @Data
        public static class Variability {
            private String value;
            private String quality;

            // Getters and setters
        }
    }
    @Data
    public static class SkyCondition {
        private CeilingHeight ceilingHeight;
        private String cavok;

        // Getters and setters
        @Data
        public static class CeilingHeight {
            private int value;
            private String quality;
            private String determination;

            // Getters and setters
        }
    }
    @Data
    public static class PrecipitationEstimatedObservation {
        private String discrepancy;
        private int estimatedWaterDepth;

        // Getters and setters
    }

    // Getters and setters for all fields
}
