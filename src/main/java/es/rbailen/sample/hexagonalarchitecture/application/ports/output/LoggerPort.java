package es.rbailen.sample.hexagonalarchitecture.application.ports.output;

public interface LoggerPort {
  void info(String message);
  void warn(String message);
  void error(String message, Throwable throwable);
}