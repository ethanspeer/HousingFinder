package housingfinder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class SuiteTest {



  @Test
  public void testToString() {
    Suite s = new Suite(1000.0, 2, 1, 3);
    String output = s.toString();
    assertEquals(
        "\nMonthly rent: $" + 1000.0 + "\nDetails: \nNumber of bedrooms: " + 2
            + "\nNumber of bathrooms: " + 1 + "\nNumber of units available: " + 3 + "\n",
        output, "Parameters properly printed");
  }



}
