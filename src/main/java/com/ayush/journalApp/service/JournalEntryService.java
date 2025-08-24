package com.ayush.journalApp.service;

import com.ayush.journalApp.entity.JournalEntry;
import com.ayush.journalApp.entity.User;
import com.ayush.journalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;



    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName)
    {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
//            user.setUserName(null);
            userService.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void updateEntry(JournalEntry journalEntry)
    {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll()
    {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalEntryByID(ObjectId myID)
    {
        return journalEntryRepository.findById(myID);
    }

    @Transactional
    public boolean deleteJournalEntryById(String userName, ObjectId myID)
    {
        try {
            User user = userService.findByUserName(userName);
            boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(myID));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(myID);
            }
            return removed;
        } catch (Exception e) {
            log.info("Error: ", e);
            throw new RuntimeException(e);
        }
    }
}
