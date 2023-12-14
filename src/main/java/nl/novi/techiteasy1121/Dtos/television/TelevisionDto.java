package nl.novi.techiteasy1121.Dtos.television;

// Deze klasse wordt gebruikt in je Get, Post en Put methodes. Overal waat je een Television als returnwaarde wilt geven.
// Een input DTO behoet geen validatie

public class TelevisionDto {
    public Long id;
    public String type;
    public String brand;
    public String name;
    public Double price;
    public Double availableSize;
    public Double refreshRate;
    public String screenType;
    public String screenQuality;
    public Boolean smartTv;
    public Boolean wifi;
    public Boolean voiceControl;
    public Boolean hdr;
    public Boolean bluetooth;
    public Boolean ambiLight;
    public Integer originalStock;
    public Integer sold;

    // We gebruiken nu geen all-args-constructor, java maakt automatisch al een no-args-constructor. Dus we hoeven geen constructor te maken.
    // Als we wel een all-args-constructor willen gebruiken (bijvoorbeeld in de service.translatetoDto() methode), dan
    // zullen we ook een no-args methode moeten maken, omdat java deze dan niet meer automatisch maakt.

//    public TelevisionDto() {
//    }
//
//    public TelevisionDto(Long id, String type, String brand, String name, Double price, Double availableSize, Double refreshRate, String screenType, String screenQuality, Boolean smartTv, Boolean wifi, Boolean voiceControl, Boolean hdr, Boolean bluetooth, Boolean ambiLight, Integer originalStock, Integer sold) {
//        this.id = id;
//        this.type = type;
//        this.brand = brand;
//        this.name = name;
//        this.price = price;
//        this.availableSize = availableSize;
//        this.refreshRate = refreshRate;
//        this.screenType = screenType;
//        this.screenQuality = screenQuality;
//        this.smartTv = smartTv;
//        this.wifi = wifi;
//        this.voiceControl = voiceControl;
//        this.hdr = hdr;
//        this.bluetooth = bluetooth;
//        this.ambiLight = ambiLight;
//        this.originalStock = originalStock;
//        this.sold = sold;
//    }

}
