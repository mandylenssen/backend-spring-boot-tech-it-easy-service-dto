package nl.novi.techiteasy1121.Dtos.television;

// Let er op dat je hier uit het "jakarta" package importeert
import jakarta.validation.constraints.*;

// Deze klasse wordt gebruikt voor je Post en Put methodes, dus daar waar je een Television als body mee geeft in Postman.

public class TelevisionInputDto {

        @NotNull(message = "Type is required") // Type moet ingevuld verplicht worden in je JSON, je krijgt een message als je dit niet doet.
        public String type;
        @NotNull(message = "Brand is required")
        public String brand;
        @Size(max = 20, message = "Name must be between 0-20 characters") // maximale lengte van de string, min is automatisch 0.
        public String name;
        @Positive(message = "Price must be higher than zero")
        public Double price;
        public Double availableSize;
        public Double refreshRate;
        public String screenType;
        public String screenQuality;
        public Boolean smartTv;
        public Boolean wifi;
        public Boolean voiceControl;
        @AssertTrue(message = "All television must be hdr minimum")
        public Boolean hdr;
        public Boolean bluetooth;
        public Boolean ambiLight;
        @PositiveOrZero(message = "Television cannot have negative stock")
        public Integer originalStock;
        public Integer sold;

}

