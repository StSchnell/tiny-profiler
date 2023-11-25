package me.bechberger;

import java.util.*;

final class MethodTableEntry {

  String method;
  long sampleCount;
  long onTopSampleCount;

  public MethodTableEntry(
    String method,
    long sampleCount,
    long onTopSampleCount
  ) {
    this.method = method;
    this.sampleCount = sampleCount;
    this.onTopSampleCount = onTopSampleCount;
  }

  String method() {
    return method;
  }

  long sampleCount() {
    return sampleCount;
  }

  long onTopSampleCount() {
    return onTopSampleCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MethodTableEntry that = (MethodTableEntry) o;
    return Objects.equals(method, that.method) &&
      Objects.equals(sampleCount, that.sampleCount) &&
      Objects.equals(onTopSampleCount, that.onTopSampleCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(method, sampleCount, onTopSampleCount);
  }

  @Override
  public String toString() {
    return "MethodTableEntry{" +
      "method='" + method + '\'' +
      ", sampleCount=" + sampleCount +
      ", onTopSampleCount=" + onTopSampleCount +
      '}';
  }

}
