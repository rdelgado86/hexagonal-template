package es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.logging;

import es.rbailen.sample.hexagonalarchitecture.application.ports.output.LoggerPort;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerAdapter implements LoggerPort {
  @Override
  public void info(String message) {
    log.info(message);
  }

  @Override
  public void warn(String message) {
    log.warn(message);
  }

  @Override
  public void error(String message, Throwable throwable) {
    log.error(message, throwable);
  }
}


