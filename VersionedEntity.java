/**
 * 
 */
package com.fremantlemedia.rms.model.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Base class for versioned Entities. Provides standard default mappings for following attributes
 * 
 *  - Version: Optimistic locking version attribute
 * @author CrowtherM
 *
 */
@MappedSuperclass
public abstract class VersionedEntity<Id> extends BaseEntity<Id> {

    private Long optimisticLockingVersion;

    @Version
    @Column(name="OPT_VER")
    public Long getOptimisticLockingVersion() {
        return optimisticLockingVersion;
    }

    public void setOptimisticLockingVersion(Long version) {
        this.optimisticLockingVersion = version;
    }
    
}
