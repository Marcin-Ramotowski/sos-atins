package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.atins.sos.data.dao.TranscriptDao;
import pl.atins.sos.model.Transcript;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class TranscriptDaoImpl implements TranscriptDao {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Optional<Transcript> findByStudentId(Long id) {
        TypedQuery<Transcript> query = em.createQuery("FROM Transcript t where t.student.id = :id", Transcript.class);
        query.setParameter("id", id);
        List<Transcript> results = query.getResultList();
        if (results.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(results.getFirst());
        }
    }

    @Override
    public void create(Transcript transcript) {
        Optional<Transcript> findTranscript = findByStudentId(transcript.getStudent().getId());
        if (findTranscript.isPresent()) {
            transcript.setId(findTranscript.get().getId());
        } else {
            em.persist(transcript);
        }
    }

    @Override
    public void deleteById(Long transcriptId) {
        Optional<Transcript> transcript = findByStudentId(transcriptId);
        transcript.ifPresent(entity -> em.remove(entity));
    }
}
