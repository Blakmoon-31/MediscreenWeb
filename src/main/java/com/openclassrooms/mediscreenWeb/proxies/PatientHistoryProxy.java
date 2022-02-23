package com.openclassrooms.mediscreenWeb.proxies;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "Mediscreen-PatientHistory", url = "localhost:8082")
public class PatientHistoryProxy {

}
