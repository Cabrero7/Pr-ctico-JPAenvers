package CONFIG;

import AUDIT.Revision;
import org.hibernate.envers.RevisionListener;

public class CustomRevisionList implements RevisionListener {
    public void newRevision(Object revisionEntity){
        final Revision revision = (Revision) revisionEntity;
    }
}
