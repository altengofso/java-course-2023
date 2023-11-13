package edu.project2.session;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {
    @Test
    void testSession() {
        Session session = new Session();
        assertThat(session.getState()).isEqualTo(SessionState.INTRO);
        session.processUserAction("");
        assertThat(session.getState()).isEqualTo(SessionState.SELECT_SIZE);
        session.processUserAction("3 3");
        assertThat(session.getState()).isEqualTo(SessionState.SELECT_GENERATOR);
        session.processUserAction("1");
        assertThat(session.getState()).isEqualTo(SessionState.SELECT_SOLVER);
        session.processUserAction("1");
        assertThat(session.getState()).isEqualTo(SessionState.AGAIN);
        session.processUserAction("y");
        assertThat(session.getState()).isEqualTo(SessionState.SELECT_SIZE);
        session.processUserAction("a b");
        assertThat(session.getState()).isEqualTo(SessionState.SELECT_SIZE);
        session.processUserAction("3 3");
        assertThat(session.getState()).isEqualTo(SessionState.SELECT_GENERATOR);
        session.processUserAction("a");
        assertThat(session.getState()).isEqualTo(SessionState.SELECT_GENERATOR);
        session.processUserAction("1");
        assertThat(session.getState()).isEqualTo(SessionState.SELECT_SOLVER);
        session.processUserAction("a");
        assertThat(session.getState()).isEqualTo(SessionState.SELECT_SOLVER);
        session.processUserAction("1");
        assertThat(session.getState()).isEqualTo(SessionState.AGAIN);
        session.processUserAction("a");
        assertThat(session.getState()).isEqualTo(SessionState.AGAIN);
        session.processUserAction("n");
        assertThat(session.getState()).isEqualTo(SessionState.QUIT);
    }
}
