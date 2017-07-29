package br.com.wpattern.multi.tenancy.user

import br.com.wpattern.multi.tenancy.utils.BaseEntity
import javax.persistence.AttributeOverride
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "tb_user")
@AttributeOverride(name = "id", column = Column(name = "pk_id"))
data class User(
        val name: String = ""
): BaseEntity<Long>()
