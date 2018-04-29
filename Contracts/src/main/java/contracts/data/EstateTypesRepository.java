package contracts.data;

import org.springframework.data.jpa.repository.JpaRepository;

import contracts.domain.EstateType;

public interface EstateTypesRepository extends JpaRepository<EstateType, String>{

}
