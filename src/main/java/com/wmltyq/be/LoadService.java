package com.wmltyq.be;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface LoadService {
    List<Step> castToStep(File file);
}
