package pl.atins.sos.data.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
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
    private EntityManager em;

    @Override
    public Optional<Transcript> findByStudentId(Long id) {
        Query query = em.createQuery("FROM Transcript t where t.student.id = :id");
        query.setParameter("id", id);
        List<Transcript> results = query.getResultList();
        if (results.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(results.get(0));
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
