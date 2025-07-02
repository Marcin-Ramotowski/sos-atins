package pl.atins.sos.data.dao;

import pl.atins.sos.model.Transcript;

import java.util.Optional;

public interface TranscriptDao {

    Optional<Transcript> findByStudentId(Long id);

    void create(Transcript transcript);

    void deleteById(Long transcriptId);
}
