package main.java.com.amazon.ask.trivia.quiz.persistence;

import com.amazon.ask.exception.PersistenceException;
import com.amazon.ask.model.Context;
import com.amazon.ask.model.Device;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.User;
import com.amazon.ask.model.interfaces.system.SystemState;

import java.util.Optional;
import java.util.function.Function;

public class PartitionKeyGenerators {

  /** Prevent instantiation */
  private PartitionKeyGenerators() {}

  /**
   * Produces a partition key from the user ID contained in an incoming request.
   * @return partition key derived from user ID
   * @throws PersistenceException if user ID cannot be retrieved
   */
  public static Function<RequestEnvelope, String> userId() {
    return r -> Optional.ofNullable(r).map(RequestEnvelope::getContext)
        .map(Context::getSystem)
        .map(SystemState::getUser)
        .map(User::getUserId)
        .orElseThrow(() -> new PersistenceException("Could not retrieve user ID from request envelope to generate persistence ID"));
  }

  /**
   * Produces a partition key from the device ID contained in an incoming request.
   * @return partition key derived from device ID
   * @throws PersistenceException if device ID cannot be retrieved
   */
  public static Function<RequestEnvelope, String> deviceId() {
    return r -> Optional.ofNullable(r).map(RequestEnvelope::getContext)
        .map(Context::getSystem)
        .map(SystemState::getDevice)
        .map(Device::getDeviceId)
        .orElseThrow(() -> new PersistenceException("Could not retrieve device ID from request envelope to generate persistence ID"));
  }

}
