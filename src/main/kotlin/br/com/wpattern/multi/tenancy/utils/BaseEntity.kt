package br.com.wpattern.multi.tenancy.utils

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.jpa.domain.AbstractPersistable
import java.io.Serializable

abstract class BaseEntity<K: Serializable> : AbstractPersistable<K>() {

    public override fun setId(id: K) =
            super.setId(id)

    @JsonIgnore
    override fun isNew() =
            super.isNew()

}