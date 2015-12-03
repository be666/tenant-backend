package com.imethod.sites.web.report.service;

import com.imethod.domain.Report;
import com.imethod.sites.web.report.dao.ReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * auth : iMethod
 * create_at:  15/11/30.
 * desc:
 * note:
 *  1.
 */
@Service
public class ReportService {

    @Autowired
    private ReportDao reportDao;

    public Report insert(Report report) {
        return reportDao.insert(report);
    }
}
