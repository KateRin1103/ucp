package by.labworks.ucp.service.impl;

import by.labworks.ucp.dto.CityDTO;
import by.labworks.ucp.dto.CompanyDTO;
import by.labworks.ucp.dto.RouteDTO;
import by.labworks.ucp.dto.mapper.CityMapper;
import by.labworks.ucp.dto.mapper.RouteMapper;
import by.labworks.ucp.entity.City;
import by.labworks.ucp.entity.Route;
import by.labworks.ucp.exception.ResourceNotFoundException;
import by.labworks.ucp.repository.CityRepository;
import by.labworks.ucp.repository.RouteRepository;
import by.labworks.ucp.service.CompanyService;
import by.labworks.ucp.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private final RouteMapper routeMapper;
    @Autowired
    private final RouteRepository routeRepository;
    @Autowired
    private final CityRepository cityRepository;
    @Autowired
    private final CityMapper cityMapper;
    @Autowired
    private final CompanyService companyService;

    public RouteServiceImpl(RouteMapper routeMapper, RouteRepository routeRepository, CityRepository cityRepository, CityMapper cityMapper, CompanyService companyService) {
        this.routeMapper = routeMapper;
        this.routeRepository = routeRepository;
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
        this.companyService = companyService;
    }

    @Override
    @Transactional
    public RouteDTO save(RouteDTO routeDto) {
        Route route = routeMapper.toEntity(routeDto);
        routeRepository.save(route);
        return routeDto;
    }

    @Override
    public void update(RouteDTO routeDto) {
        Route route = routeRepository.findById(routeDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(routeDto.getId()));

        routeRepository.save(routeMapper.toEntity(routeDto));
    }

    @Override
    public void delete(Long routeId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException(routeId));
        routeRepository.delete(route);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RouteDTO> findAll() {
        List<Route> routes = routeRepository.findAll();
        return routeMapper.toDtoList(routes);
    }

    @Override
    @Transactional(readOnly = true)
    public RouteDTO findById(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return routeMapper.toDto(route);
    }

    @Override
    public RouteDTO findByName(String name) {
        Route route = routeRepository.findByName(name);
        return routeMapper.toDto(route);
    }

    @Override
    public RouteDTO findByCityA(CityDTO cityA) {
        Route route = routeRepository.findByCityA(cityMapper.toEntity(cityA));
        return routeMapper.toDto(route);
    }

    @Override
    public RouteDTO findByCityB(CityDTO cityB) {
        Route route = routeRepository.findByCityB(cityMapper.toEntity(cityB));
        return routeMapper.toDto(route);
    }

    @Override
    public RouteDTO findByCityAAndCityB(CityDTO cityA, CityDTO cityB) {
        City citya = cityMapper.toEntity(cityA);
        City cityb = cityMapper.toEntity(cityB);

        return routeMapper.toDto(routeRepository.findByCityAAndCityB(citya, cityb));

    }

    @Override
    public Map<CompanyDTO, Map<List<CityDTO>, Double>> findRoute(CityDTO cityA, CityDTO cityB) {
        List<CompanyDTO> companies = companyService.findAll();
        Map<CompanyDTO, Map<List<CityDTO>, Double>> result = new HashMap<>();
        Map<List<CityDTO>, Double> resultValue = new HashMap<>();
        for (CompanyDTO company : companies) {
            List<CityDTO> companyCities = new ArrayList<>();
            List<RouteDTO> routes = company.getRoutes();
            for (int i = 1; i < routes.size(); i++) {
                RouteDTO route = routes.get(i);
                if (!companyCities.contains(route.getCityA())) {
                    companyCities.add(route.getCityA());
                }

                if (!companyCities.contains(route.getCityB())) {
                    companyCities.add(route.getCityB());
                }
            }

            int n = companyCities.size();
            double[][] MatrixVeight = new double[n][n]; //матрица размером с кол-во городов

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (company.getRoutes().contains(this.findByCityAAndCityB(companyCities.get(i), companyCities.get(j)))) {
                        MatrixVeight[i][j] = this.findByCityAAndCityB(companyCities.get(i), companyCities.get(j)).getDistance();
                    } else {
                        MatrixVeight[i][j] = 100000000;
                    }
                }
            }

            int[][] MatrixHistory = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (company.getRoutes().contains(this.findByCityAAndCityB(companyCities.get(i), companyCities.get(j)))) {

                        for (RouteDTO route : company.getRoutes()) {
                            if (route.getCityA().equals(companyCities.get(i)) && route.getCityB().equals(companyCities.get(j))) {
                                MatrixHistory[i][j] = j + 1;
                            }
                        }
                    } else {
                        MatrixHistory[i][j] = 0;
                    }
                }
            }


            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.printf("%.0f", MatrixVeight[i][j]);
                    System.out.printf("-------");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.printf("%3d", MatrixHistory[i][j]);
                    System.out.printf("  ");
                }
                System.out.println();
            }


            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (MatrixVeight[i][j] > -1) {
                        for (int w = 0; w < n; w++) {
                            if (MatrixVeight[i][w] > MatrixVeight[i][j] + MatrixVeight[j][w]) {
                                MatrixVeight[i][w] = MatrixVeight[i][j] + MatrixVeight[j][w];
                                MatrixHistory[i][w] = MatrixHistory[i][j];
                            }
                        }

                    }


                }
            }
            System.out.println();
            System.out.println();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        MatrixVeight[i][j] = 0;
                    }
                    System.out.printf("%3.0f", MatrixVeight[i][j]);
                    System.out.printf("-------");
                }
                System.out.println();
            }

            System.out.println();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.printf("%3d", MatrixHistory[i][j]);
                }
                System.out.println();
            }


            int start = -1;
            int end = -1;


            for (int i = 0; i < companyCities.size(); i++) {
                if (companyCities.get(i).equals(cityA)) {
                    start = i;
                }
                if (companyCities.get(i).equals(cityB)) {
                    end = i;
                }
            }

            Double distance = MatrixVeight[start][end];
            System.out.println(MatrixVeight[start][end]);
            List<Integer> Path = new ArrayList<>();

            while (start >= end) {
                Path.add(start);

                start = MatrixHistory[start][end] - 1;
            }

            Path.add(end);

            System.out.println(Path);
            List<CityDTO> resultCities = new ArrayList<>();
            for (int i : Path) {
                System.out.println(companyCities.get(i).getName());
                resultCities.add(companyCities.get(i));
            }

            resultValue.put(resultCities, distance);
            result.put(company, resultValue);
             break;
        }
        return result;
    }

    /*  @Override
    public Map<CompanyDTO,Map<List<CityDTO>,Double>> findRoute(CityDTO cityA, CityDTO cityB) {
        List<CompanyDTO> companies = companyService.findAll();
        Map<CompanyDTO,Map<List<CityDTO>,Double>> result = new HashMap<>();
        Map<List<CityDTO>,Double> resultValue = new HashMap<>();
        for (CompanyDTO company : companies){
            List<CityDTO> companyCities = new ArrayList<>();
            for(RouteDTO route : company.getRoutes()){
               if(!companyCities.contains(route.getCityA())){
                   companyCities.add(route.getCityA());
               }

                if(!companyCities.contains(route.getCityB())){
                    companyCities.add(route.getCityB());
                }
            }

            int n = companyCities.size();
            double[][] MatrixVeight = new double[n][n];

            for(int i = 0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(company.getRoutes().contains(this.findByCityAAndCityB(companyCities.get(i),companyCities.get(j)))){
                        MatrixVeight[i][j]=this.findByCityAAndCityB(companyCities.get(i),companyCities.get(j)).getDistance();
                    }
                    else{
                        MatrixVeight[i][j]=100000000;
                    }
                }
            }

            int[][] MatrixHistory = new int[n][n];

            for(int i = 0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(company.getRoutes().contains(this.findByCityAAndCityB(companyCities.get(i),companyCities.get(j)))){

                        for(RouteDTO route : company.getRoutes()) {
                            if(route.getCityA().equals(companyCities.get(i))&&route.getCityB().equals(companyCities.get(j)))
                            MatrixHistory[i][j] = j+1;
                        }
                    }
                    else{
                        MatrixHistory[i][j]=0;
                    }
                }
            }




            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    System.out.printf("%.0f", MatrixVeight[i][j]);
                    System.out.printf("-------");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    System.out.printf("%3d", MatrixHistory[i][j]);
                    System.out.printf("  ");
                }
                System.out.println();
            }



            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (MatrixVeight[i][j]>-1){
                        for (int w = 0; w < n; w++){
                            if (MatrixVeight[i][w] > MatrixVeight[i][j] + MatrixVeight[j][w]) {
                                MatrixVeight[i][w] = MatrixVeight[i][j] + MatrixVeight[j][w];
                                MatrixHistory[i][w] = MatrixHistory[i][j];
                            }
                        }

                    }


                }
            }
            System.out.println();
            System.out.println();
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if(i==j){
                        MatrixVeight[i][j] = 0;
                    }
                    System.out.printf("%3.0f", MatrixVeight[i][j]);
                    System.out.printf("-------");
                }
                System.out.println();
            }

            System.out.println();
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    System.out.printf("%3d", MatrixHistory[i][j]);
                }
                System.out.println();
            }


int start = -1;
int end = -1;


for(int i=0; i<companyCities.size();i++){
    if(companyCities.get(i).equals(cityA)){
        start=i;
    }
    if(companyCities.get(i).equals(cityB)){
        end=i;
    }
}

Double distance = MatrixVeight[start][end];
            System.out.println(MatrixVeight[start][end]);
            List<Integer> Path = new ArrayList<>();

            while (start != end)
            {
                Path.add(start);

                start = MatrixHistory[start][end]-1;
            }

            Path.add(end);

            System.out.println(Path);
List<CityDTO> resultCities = new ArrayList<>();
            for(int i: Path){
                System.out.println(companyCities.get(i).getName());
                resultCities.add(companyCities.get(i));
            }

            resultValue.put(resultCities,distance);
            result.put(company,resultValue);

        }
        return result;
    }*/
}

