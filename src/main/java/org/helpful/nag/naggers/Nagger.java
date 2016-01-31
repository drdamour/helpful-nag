package org.helpful.nag.naggers;

import java.io.IOException;
import java.util.stream.Stream;

public interface Nagger {
    Stream<Nag> findNags() throws Exception;
}
