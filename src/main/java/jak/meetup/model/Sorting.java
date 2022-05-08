/*
 * Swagger Roundtable
 * Requirements for roundtable demo app
 *
 * OpenAPI spec version: 1.0.0
 * Contact: mcerkez88@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package jak.meetup.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * Sorting
 */


public class Sorting {
  @JsonProperty("arraySize")
  private Long arraySize = null;

  public Sorting arraySize(Long arraySize) {
    this.arraySize = arraySize;
    return this;
  }

   /**
   * Get arraySize
   * @return arraySize
  **/
  @Schema(required = true, description = "")
  public Long getArraySize() {
    return arraySize;
  }

  public void setArraySize(Long arraySize) {
    this.arraySize = arraySize;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Sorting sorting = (Sorting) o;
    return Objects.equals(this.arraySize, sorting.arraySize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(arraySize);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Sorting {\n");
    
    sb.append("    arraySize: ").append(toIndentedString(arraySize)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}