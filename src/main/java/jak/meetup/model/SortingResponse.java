package jak.meetup.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * SortingResponse
 */

public class SortingResponse {

    private Long timeSpentInMs = null;
    private Long timeSpentCreatingListInMs = null;
    private Long timeSpentSortingListInMs = null;

    public SortingResponse timeSpentInMs(Long timeSpentInMs) {
        this.timeSpentInMs = timeSpentInMs;
        return this;
    }

    public SortingResponse timeSpentCreatingListInMs(Long timeSpentCreatingListInMs) {
        this.timeSpentCreatingListInMs = timeSpentCreatingListInMs;
        return this;
    }

    public SortingResponse timeSpentSortingListInMs(Long timeSpentSortingListInMs) {
        this.timeSpentSortingListInMs = timeSpentSortingListInMs;
        return this;
    }

    /**
     * Get timeSpentInMs
     *
     * @return timeSpentInMs
     **/
    @Schema(required = true, description = "")
    public Long getTimeSpentInMs() {
        return timeSpentInMs;
    }

    public void setTimeSpentInMs(Long timeSpentInMs) {
        this.timeSpentInMs = timeSpentInMs;
    }

    public Long getTimeSpentCreatingListInMs() {
        return timeSpentCreatingListInMs;
    }

    public void setTimeSpentCreatingListInMs(Long timeSpentCreatingListInMs) {
        this.timeSpentCreatingListInMs = timeSpentCreatingListInMs;
    }

    public Long getTimeSpentSortingListInMs() {
        return timeSpentSortingListInMs;
    }

    public void setTimeSpentSortingListInMs(Long timeSpentSortingListInMs) {
        this.timeSpentSortingListInMs = timeSpentSortingListInMs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SortingResponse sortingResponse = (SortingResponse) o;
        return Objects.equals(this.timeSpentInMs, sortingResponse.timeSpentInMs)
                && Objects.equals(this.timeSpentCreatingListInMs, sortingResponse.timeSpentCreatingListInMs)
                && Objects.equals(this.timeSpentSortingListInMs, sortingResponse.timeSpentSortingListInMs);
    }

    @Override
    public int hashCode() {
        int result = timeSpentInMs != null ? timeSpentInMs.hashCode() : 0;
        result = 31 * result + (timeSpentCreatingListInMs != null ? timeSpentCreatingListInMs.hashCode() : 0);
        result = 31 * result + (timeSpentSortingListInMs != null ? timeSpentSortingListInMs.hashCode() : 0);
        return result;
    }

}
