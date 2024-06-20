package sample.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.Repository.AddNewItemRepository;

@Service
public class AddNewItemService {
	@Autowired
	AddNewItemRepository addNewItemRepository;
}