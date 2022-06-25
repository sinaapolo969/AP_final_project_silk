package Model;

public enum Categories
{
    RealEstate("Apartment", "Domain", "Studio Flat"),
    Vehicle("Truck", "Car", "Cycles"),
    Digital("Computer", "Mobile & Tablet", "Video & Audio Equipments"),
    Furniture("Electronic", "Decorative", "Others"),
    Service("Beauty", "Services", "Financial & Educational"),
    Entertainment("Sports", "Musical", "Others"),
    Recruitment("Education", "ُServices", "industrial"),
    Personal("Fashion", "Beauty", "Stationery");

    final String firstSubCategory;
    final String secondSubCategory;
    final String thirdSubCategory;

    Categories(String firstSubCategory, String secondSubCategory, String thirdSubCategory)
    {
        this.firstSubCategory = firstSubCategory;
        this.secondSubCategory =secondSubCategory;
        this.thirdSubCategory = thirdSubCategory;
    }
}
