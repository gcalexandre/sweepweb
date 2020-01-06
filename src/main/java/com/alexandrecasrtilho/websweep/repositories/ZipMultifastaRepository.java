package com.alexandrecasrtilho.websweep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexandrecasrtilho.websweep.domain.UploadZipMultifasta;
@Repository
public interface ZipMultifastaRepository extends JpaRepository<UploadZipMultifasta, String> {

}
