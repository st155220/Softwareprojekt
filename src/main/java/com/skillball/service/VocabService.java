package com.skillball.service;

import com.skillball.entity.Vocab;
import com.skillball.repository.VocabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
//
@Service
public class VocabService {

    @Autowired
    private VocabRepository vocabRepository;
    private List<Vocab> adminVocabList;
    private List<Vocab> userVocabList;
    private Vocab currentVocab;

    public Vocab saveVocab(Vocab vocab) {
        return vocabRepository.save(vocab);
    }

    public void deleteVocab(Vocab vocab) {
        vocabRepository.delete(vocab);
    }

    public List<Vocab> findAllVocab() {
        return vocabRepository.findAll();
    }

    public List<Vocab> getCurrentVocabList(boolean admin) {
        if (admin) {
            return adminVocabList;
        } else {
            return userVocabList;
        }
    }

    public void setCurrentVocabList(List<Vocab> currentVocabList, boolean admin) {
        if (admin) {
            this.adminVocabList = currentVocabList;
        } else {
            this.userVocabList = currentVocabList;
        }
    }

    public Vocab getCurrentVocab() {
        return currentVocab;
    }

    public void setCurrentVocab(Vocab currentVocab) {
        this.currentVocab = currentVocab;
    }

    public void updateCurrentVocabList(String language, String level, int index, boolean admin) {
        List<Vocab> vocabList = findAllVocab();
        vocabList.sort(new VocabComparator());
        for (int i = vocabList.size() - 1; i >= 0; i--) {
            Vocab vocab = vocabList.get(i);
            if (!vocab.getLanguage().equals(language) || !vocab.getLevel().equals(level) || vocab.getIndex() != index) {
                vocabList.remove(i);
            }
        }
        if (vocabList.isEmpty()) {
            Vocab vocab = new Vocab();
            vocab.setLanguage(language);
            vocab.setLevel(level);
            vocab.setIndex(index);
            vocab.setEnglish("<<EMPTY>>");
            vocab.setTranslation("<<EMPTY>>");
            vocab.setPosition(0);
            vocabList.add(vocab);
        }
        setCurrentVocabList(vocabList, admin);
    }

    public List<String> createTemplateList(boolean admin) {
        List<String> templateList = new ArrayList<>();
        for (Vocab vocab : getCurrentVocabList(admin)) {
            if (admin) {
                templateList.add(vocab.getPosition() + ". " + vocab.getEnglish() + " ---- " + vocab.getTranslation());
            } else {
                templateList.add(vocab.getEnglish() + " ---- " + vocab.getTranslation());
            }
        }
        return templateList;
    }

    private class VocabComparator implements Comparator<Vocab> {
        @Override
        public int compare(Vocab vocab1, Vocab vocab2) {
            return vocab1.getPosition() - vocab2.getPosition();
        }
    }
}