package br.com.wpattern.multi.tenancy.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>
