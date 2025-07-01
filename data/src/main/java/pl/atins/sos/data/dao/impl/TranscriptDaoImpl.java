package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.TranscriptDao;
import pl.atins.sos.model.Transcript;

import java.util.Optional;

@Repository
public class TranscriptDaoImpl implements TranscriptDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Transcript> findById(Long id) {
        Transcript transcript = em.find(Transcript.class, id);
        return Optional.of(transcript);
    }

    @Override
    public void createTranscript(Transcript transcript) {
        em.persist(transcript);
    }

    @Override
    public Optional<Transcript> updateTranscript(Transcript transcript) {
        return Optional.of(em.merge(transcript));
    }

    @Override
    public void deleteTranscript(Transcript transcript) {
        em.remove(transcript);
    }
}
