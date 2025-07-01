package pl.atins.sos.data.dao;

import pl.atins.sos.model.Transcript;

import java.util.Optional;

public interface TranscriptDao {

    Optional<Transcript> findById(Long id);

    void createTranscript(Transcript transcript);

    Optional<Transcript> updateTranscript(Transcript transcript);

    void deleteTranscript(Transcript transcript);
}
