package edu.ub.pis2425.recyclerviewexample.data.services.mockdata;

import java.util.HashMap;

import edu.ub.pis2425.recyclerviewexample.domain.Price;
import edu.ub.pis2425.recyclerviewexample.domain.Product;

public class MockProductsHashMap extends HashMap<String, Product> {
  /**
   * Empty constructor
   */
  public MockProductsHashMap() {
    super();
    mockInit();
  }

  /**
   * Initializes the mock data
   */
  private void mockInit() {
    // Create Product objects directly from JSON content
    Product[] mockInitProducts = new Product[] {
        new Product(
            "ce7bf261-d2c6-4cf7-9c54-b5d446d9f43e",
            "BlinkSync Synchronized Blinking Eyelashes",
            "blinksync synchronized blinking eyelashes",
            "Experience the future of beauty with BlinkSync Synchronized Blinking Eyelashes. These innovative eyelashes synchronize their blinking pattern, adding a captivating charm to your eyes. Embrace a new level of expressiveness with this unique cosmetic accessory.",
            new Price(9.99, "EUR"),
            "https://i.ibb.co/SJczF8M/P001.jpg",
            false
        ),
        new Product(
            "05fadf8c-4802-4620-9540-f7e33d5dfdc1",
            "Byson Vacuum Cleaner",
            "byson vacuum cleaner",
            "Introducing the Byson Vacuum Cleaner, a powerhouse for your cleaning needs. With advanced suction technology, it effortlessly tackles dust and debris, leaving your space immaculately clean. Make cleaning a breeze with the reliable performance of Byson.",
            new Price(715.49, "EUR"),
            "https://i.ibb.co/qJPLqfj/P002.jpg",
            true
        ),
        new Product(
            "a529c2dc-74b1-4016-a2ac-632a32d6a072",
            "ChocoChill Chocolate Ice Cream Heater",
            "chocochill chocolate ice cream heater",
            "Indulge in the decadent delight of warm chocolate-covered ice cream with the ChocoChill Chocolate Ice Cream Heater. This innovative device warms your favorite frozen treat, turning every bite into a heavenly experience. A must-have for chocolate enthusiasts.",
            new Price(33.75, "EUR"),
            "https://i.ibb.co/9YKGrZk/P003.jpg",
            true
        ),
        new Product(
            "75280f96-4f0c-47af-85a5-f858d10d61f9",
            "CrazyCat Laser Pointer Glasses",
            "crazycat laser pointer glasses",
            "Unleash the playful side of your feline friends with CrazyCat Laser Pointer Glasses. Designed for interactive play, these glasses emit laser beams that captivate your cat's attention, providing hours of entertainment. Watch your cat chase and pounce in joy!",
            new Price(16.50, "EUR"),
            "https://i.ibb.co/Q6MddgK/P004.jpg",
            true
        ),
        new Product(
            "e17c758f-cb19-4757-8969-cdfecc59c182",
            "FizzBuzz Energy Drink",
            "fizzbuzz energy drink",
            "Recharge your energy levels with FizzBuzz Energy Drink. This invigorating beverage combines a burst of flavors with a revitalizing fizz, giving you the boost you need to tackle the day. Stay refreshed and energized with FizzBuzz.",
            new Price(2.49, "EUR"),
            "https://i.ibb.co/1mg3s1V/P005.jpg",
            false
        ),
        new Product(
            "36bfe36b-55e2-4c0f-9a00-99b42a5b41ab",
            "Fluffinator Robotic Feather Duster",
            "fluffinator robotic feather duster",
            "Say goodbye to dust with the Fluffinator Robotic Feather Duster. This intelligent cleaning companion gracefully navigates your space, gently removing dust and debris with its soft feathered brushes. Enjoy a cleaner home without the effort.",
            new Price(54.50, "EUR"),
            "https://i.ibb.co/HPRrqts/P006.jpg",
            true
        ),
        new Product(
            "1f39972c-e04c-43c7-ad29-b8cfa00d3e4a",
            "FOSCH Dishwasher",
            "fosch dishwasher",
            "Simplify your kitchen chores with the FOSCH Dishwasher. This high-performance appliance effortlessly cleans and sanitizes your dishes, saving you time and effort. Upgrade your kitchen with the convenience of FOSCH.",
            new Price(549.99, "EUR"),
            "https://i.ibb.co/pbswLc4/P007.jpg",
            true
        ),
        new Product(
            "459cfc71-dd49-4212-8f39-90af3af8b9e0",
            "FunkyFog Bubblegum Ultra-scented Fog Machine",
            "funkyfog bubblegum ultra-scented fog machine",
            "Transform any space into a whimsical wonderland with the FunkyFog Bubblegum Ultra-scented Fog Machine. Emitting fragrant and colorful fog, this machine adds a touch of magic to events and gatherings. Create a sensory experience like never before.",
            new Price(79.99, "EUR"),
            "https://i.ibb.co/J51R3PD/P008.jpg",
            true
        ),
        new Product(
            "e5a79a8f-5c7f-4525-ad9a-4acdcfecd19b",
            "Galactic Pillow Nebula",
            "galactic pillow nebula",
            "Drift into a cosmic dreamland with the Galactic Pillow Nebula. This celestial-themed pillow combines comfort and style, featuring a mesmerizing nebula print. Elevate your relaxation experience with the enchanting design of the Galactic Pillow Nebula.",
            new Price(16.99, "EUR"),
            "https://i.ibb.co/H7GnSbC/P009.jpg",
            false
        ),
        new Product(
            "980b5391-218a-40e7-91fb-a63df0909a63",
            "GiggleGoggles Laugh-Activated Glasses",
            "gigglegoggles laugh-activated glasses",
            "Spread joy and laughter with GiggleGoggles Laugh-Activated Glasses. Equipped with innovative sensors, these glasses respond to laughter, triggering amusing visual effects. Wear them to add a playful touch to any social gathering.",
            new Price(45.00, "EUR"),
            "https://i.ibb.co/njR56rC/P010.jpg",
            true
        ),
        new Product(
            "13b86db0-97da-4c08-8a53-e4aef9ded3f9",
            "HuggleSnuggle Wearable Blanket",
            "hugglesnuggle wearable blanket",
            "Embrace warmth and coziness with the HuggleSnuggle Wearable Blanket. Perfect for chilly evenings, this blanket features a wearable design, allowing you to stay snug while maintaining mobility. Experience the ultimate comfort with HuggleSnuggle.",
            new Price(33.99, "EUR"),
            "https://i.ibb.co/mHfg2q1/P011.jpg",
            false
        ),
        new Product(
            "3b7d9d54-d63d-406f-81fe-d578d6b7ca95",
            "iPricey Phone",
            "ipricey phone",
            "Step into the future of communication with the iPricey Phone. Packed with cutting-edge features and a sleek design, this smartphone redefines the mobile experience. Stay connected in style with the premium craftsmanship of the iPricey Phone.",
            new Price(449.99, "EUR"),
            "https://i.ibb.co/61Hsrqj/P012.jpg",
            true
        )
    };

    // Print out the products for verification
    for (Product product : mockInitProducts) {
      put(product.getId(), product);
    }
  }
}
